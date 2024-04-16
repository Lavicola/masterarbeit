package fau.deviceConfiguration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ip;

    // if the order goes from low to high we always know the last element is the current element
    @OrderBy("id asc")
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Costmodel> costModels;

    @OrderBy("id asc")
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<RPUCapabilities> capabilities;

    public List<Port> getPorts() {
        return ports;
    }

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Port> ports = new ArrayList<>();


    public Device(String name, String ip) {
        this.name = name;
        this.ip = ip;
        this.costModels = new ArrayList<Costmodel>();
        this.capabilities = new ArrayList<RPUCapabilities>();
        this.ports = new ArrayList<Port>();
    }


    @Transient
    public boolean addCostmodel(Costmodel p_costmodel) {
        List<Costmodel> current_costmodels = this.getCostModels();
        if (current_costmodels.isEmpty()) {
            // first one to set!
            p_costmodel.setVersion(1);
            p_costmodel.setDevice(this);
            this.costModels.add(p_costmodel);
            return true;
        } else {
            // already elements inside
            if (current_costmodels.get(current_costmodels.size() - 1).getCostmodelHash().equals(p_costmodel.getCostmodelHash())) {
                // last element has the same hash as the new one --> it´s the same capabilities
                return false;
            }
            // actually a new capability
            p_costmodel.setVersion(current_costmodels.size() + 1);
            p_costmodel.setDevice(this);
            this.costModels.add(p_costmodel);
            return true;
        }
    }

    @Transient
    public boolean addRPUCapabilities(RPUCapabilities rpuCapabilities) {
        List<RPUCapabilities> current_capabilities = this.getCapabilities();
        if (current_capabilities.isEmpty()) {
            // first one to set!
            rpuCapabilities.setVersion(1);
            rpuCapabilities.setDevice(this);
            this.capabilities.add(rpuCapabilities);
            return true;
        } else {
            // already elements inside
            if (current_capabilities.get(current_capabilities.size() - 1).getCapabilitiesHash().equals(rpuCapabilities.getCapabilitiesHash())) {
                // last element has the same hash as the new one --> it´s the same capabilities
                return false;
            }
            // actually a new capability
            rpuCapabilities.setDevice(this);
            rpuCapabilities.setVersion(current_capabilities.size() + 1);
            this.capabilities.add(rpuCapabilities);
            return true;

        }
    }


    public Device() {

    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public List<Costmodel> getCostModels() {
        return this.costModels;
    }

    public void setCapabilities(ArrayList<RPUCapabilities> capabilities) {
        this.capabilities = capabilities;
    }

    public void setCostModels(ArrayList<Costmodel> costModels) {
        this.costModels = costModels;
    }

    public List<RPUCapabilities> getCapabilities() {
        return this.capabilities;
    }


    @Transient
    public boolean setPorts(List<Port> ports) {
        HashMap<Integer, Port> hashmap = new HashMap<Integer, Port>();
        List<Port> l_ports = new ArrayList<Port>();
        for (Port port : ports) {
            if (hashmap.containsKey(port.number)) {
                // two with same number are not allowed
                return false;
            }
            hashmap.put(port.number, port);
            port.setDevice(this);
            l_ports.add(port);
        }
        // now delete old ports
        for(Port port : this.getPorts()){
            port.setDevice(null);
        }
        this.ports.clear();

        this.ports = l_ports;
        return true;
    }

    @Transient
    public List<Port> getDatastreamPorts(){
        return this.ports.stream()
                .filter(port -> port.getDatastream() != null).toList();

    }

    @Transient
    public List<Port> getNormalPorts(){
        return this.ports.stream()
                .filter(port -> port.getDatastream() == null).toList();

    }


}
