package normal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class JacksonTest {

  private ObjectMapper mapper;

  @Before
  public void initField() {
    mapper = new ObjectMapper();
  }

  @Test
  public void jacksonTest() throws Exception {
    String s = "[1,2,3]";
    List<Integer> x = mapper.readValue(s, new TypeReference<List<Integer>>() {
    });
    System.out.println(x);

  }
}
