package anfankus.dao;

import anfankus.dao.inner.ScoreValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Score {

  private static ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
  }

  private String scoreid;
  private String solutionid;
  private ScoreValue value;

  public void setValueString(String json) throws Exception {
    value = mapper.readValue(json, ScoreValue.class);
  }

  @JsonIgnore
  public String getValueString() throws Exception {
    return mapper.writeValueAsString(value);
  }

}
