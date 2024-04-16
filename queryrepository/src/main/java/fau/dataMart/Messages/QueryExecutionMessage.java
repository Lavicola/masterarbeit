package fau.dataMart.Messages;

import javax.annotation.processing.Generated;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Generated(value="com.asyncapi.generator.template.spring", date="2024-02-07T01:13:06.599Z")
public class QueryExecutionMessage {
    private @Valid QueryExecutionPayload payload;

    public QueryExecutionPayload getPayload() {
        return payload;
    }

    public void setPayload(QueryExecutionPayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryExecutionMessage event = (QueryExecutionMessage) o;
        return Objects.equals(this.payload, event.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload);
    }

    @Override
    public String toString() {
        return "class QueryExecutionMessage {\n" +
                "    payload: " + toIndentedString(payload) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}