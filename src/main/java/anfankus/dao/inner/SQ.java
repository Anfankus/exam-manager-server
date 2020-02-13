package anfankus.dao.inner;

import java.util.List;
import lombok.Data;

@Data
public class SQ {

  private String title;
  private String answer;
  private List<String> options;
  private Integer maxscore;
}
