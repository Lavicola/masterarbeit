package fau.deviceConfiguration.models;

import fau.datastreamMetric.models.Datastream;
import jakarta.persistence.*;

@Entity
/* I leave that here. using my Logic in Device to setPorts is easier than using this
Because: Either remove Ports and then store new ones + fallback OR
for every Port check if something changed.
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"device_name", "number"})
})
*/
@Inheritance(strategy = InheritanceType.JOINED)
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;


    @ManyToOne
    @JoinColumn(name = "device_name")
    protected Device device;
    protected String description;
    protected int number;
    protected String ip;

    @ManyToOne
    @JoinColumn(name = "datastream_id")
    private Datastream datastream;


    public Device getDevice() {
        return device;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    protected String internalIp;

    public Port(int number, String description,String ip,String internalIp) {
        this.number = number;
        this.description = description;
        this.ip = ip;
        this.internalIp = internalIp;
    }

    public Port(Device device, int number, String description,String ip,String internalIp) {
        this(number, description,ip,internalIp);
        this.device = device;
    }

    public Port(int number, String description,String ip,String internalIp, Datastream datastream) {
        this(number, description,ip,internalIp);
        this.datastream = datastream;
    }



    public Port() {

    }

    public int getPortNumber() {
        return this.number;
    }

    public void setDevice(Device device){
        this.device = device;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Portnumber: ").append(this.getPortNumber()).append("\n");
        sb.append("Port used in: ").append(this.device).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        return sb.toString();
    }


    public Datastream getDatastream() {
        return datastream;
    }

    public void setDatastream(Datastream datastream) {
        this.datastream = datastream;
    }
}
