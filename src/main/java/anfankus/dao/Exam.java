package anfankus.dao;

import anfankus.dao.inner.SAQ;
import anfankus.dao.inner.SQ;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Data;

@Data
public class Exam {

  private static ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
  }

  private String examid;
  private String examno;
  private String examname;
  private Integer starttime;
  private Integer examtime;

  private String description;
  private Integer totalscore;
  private List<SQ> sq;
  private List<SAQ> saq;
  private String userid;

  public void setSqstring(String json) throws Exception {
    sq = mapper.readValue(json, new TypeReference<List<SQ>>() {
    });
  }

  public void setSaqstring(String json) throws Exception {
    saq = mapper.readValue(json, new TypeReference<List<SAQ>>() {
    });
  }

  @JsonIgnore
  public String getSqstring() throws Exception {
    return mapper.writeValueAsString(sq);
  }

  @JsonIgnore
  public String getSaqstring() throws Exception {
    return mapper.writeValueAsString(saq);
  }

}
