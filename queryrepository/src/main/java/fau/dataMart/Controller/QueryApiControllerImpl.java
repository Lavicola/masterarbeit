package fau.dataMart.Controller;

import fau.dataMart.Repository.ExecutionplanRepository;
import fau.dataMart.Repository.QueryRepository;
import fau.dataMart.Service.QueryFeatureService;
import fau.dataMart.dto.QueryDTO;
import fau.dataMart.dto.QueryDTOPage;
import fau.dataMart.models.Executionplan;
import fau.dataMart.models.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.data.domain.Pageable;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QueryApiControllerImpl implements QueryApiDelegate {


    @Autowired
    private QueryFeatureService queryFeatureService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<QueryDTOPage> queryGet(LocalDateTime startDate,
                                                 LocalDateTime endDate,
                                                 Integer page,
                                                 Integer size,
                                                 String sort,
                                                 List<String> tablenames) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Query> queryPage = queryFeatureService.findByMetadataInAndTimestampBetween(tablenames, startDate, endDate, pageable);
        List<QueryDTO> queryDTOs = new ArrayList<>();
        QueryDTO queryDTO;
        QueryDTOPage queryDTOPage = new QueryDTOPage();
        for (Query query : queryPage) {
            queryDTO = this.modelMapper.map(query, QueryDTO.class);
            queryDTO.setExecutionplanHash(query.getExecutionplan().getHash());
            queryDTO.setExecutionplans(this.queryFeatureService.getAllExecutionplansForaQuery(query));
            queryDTOs.add(queryDTO);
        }
        queryDTOPage.setQueries(queryDTOs);
        queryDTOPage.setTotalPages(queryPage.getTotalPages());

        return ResponseEntity.status(HttpStatus.OK).body(queryDTOPage);

    }

    public ResponseEntity<QueryDTO> queryPost(QueryDTO queryDTO) {
        //send and check if we get a Query back
        Query query = queryFeatureService.sendQueryAndWait(queryDTO.getStatement());
        if (query != null) {
            // Query was found
            List<Executionplan> executionplans = this.queryFeatureService.getAllExecutionplansForaQuery(query);
            List<fau.dataMart.dto.Executionplan> plans = executionplans.stream()
                    .map(source -> this.modelMapper.map(source, fau.dataMart.dto.Executionplan.class)).toList();
            queryDTO.setStatementHash(query.getStatementHash());
            queryDTO.setExecutionplans(executionplans);
            return ResponseEntity.status(HttpStatus.OK).body(queryDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


