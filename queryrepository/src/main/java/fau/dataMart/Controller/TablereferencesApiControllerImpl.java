package fau.dataMart.Controller;

import fau.dataMart.Repository.MetadataRepository;
import fau.dataMart.models.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TablereferencesApiControllerImpl implements TablereferencesApiDelegate{

    // I know not layer like, but for one method not worth it
    @Autowired
    MetadataRepository metadataRepository;


        @Override
        public ResponseEntity<List<String>> tablereferencesGet() {

        List<String> tables = new ArrayList<>();
        for (Metadata metadata : this.metadataRepository.findAll()) {
            tables.add(metadata.getName());
        }

        return ResponseEntity.status(HttpStatus.OK).body(tables);

    }


}
