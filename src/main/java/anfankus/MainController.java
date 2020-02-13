package anfankus;

import anfankus.dao.Exam;
import anfankus.dao.Score;
import anfankus.dao.Solution;
import anfankus.dao.User;
import anfankus.model.NormalRes;
import anfankus.model.SignInRes;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
//@Service
public class MainController {


  private IMainMapper mainMapper;

  public MainController(IMainMapper mainMapper) {
    this.mainMapper = mainMapper;
  }

  @GetMapping("test")
  public Object testRounting() throws Exception {
    return mainMapper.getSolutionList();
  }

  @GetMapping("/user")
  public User UserRouting(@RequestParam("userid") String userid) {
    try {
      return mainMapper.getUserById2(userid);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @PostMapping("/signup")
  public NormalRes SignUpRouting(@RequestBody User user) {
    try {
      int ret = mainMapper.addUser(user);
      System.out.println(ret);
      return new NormalRes(true, "");
    } catch (DuplicateKeyException ex) {
      return new NormalRes(false, "用户名已存在");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new NormalRes(false, "服务器错误");
    }
  }

  @GetMapping("/signin")
  public SignInRes SignInRouting(@RequestParam("userid") String userid,
      @RequestParam("password") String password) {

    try {
      User ret = mainMapper.verifyUser(userid);
      if (!ret.getPassword().equals(password)) {
        return new SignInRes(false, null, null, "用户名密码错误");
      } else {
        return new SignInRes(true, ret.getUsertype(), ret.getUsername(), "");
      }
    } catch (NullPointerException ex) {
      return new SignInRes(false, null, null, "用户名密码错误");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new SignInRes(false, null, null, "服务器错误");
    }
  }

  @GetMapping("/exam-meta-list")
  public List<Exam> ExamMetaListRouting(@RequestParam("userid") String userid) {
    return mainMapper.getExamMetaListByUserid(userid);
  }

  @GetMapping("/exam")
  public Exam ExamRouting(@RequestParam("examid") String id) {
    try {
      return mainMapper.getExamById(id);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @PostMapping("/new-exam")
  public NormalRes NewExamRouting(@RequestBody Exam exam) {
    try {
      mainMapper.addExam(exam);
      return new NormalRes(true, "");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new NormalRes(false, "服务器错误");
    }
  }

  @PutMapping("/update-exam")
  public NormalRes UpdateExamRouting(@RequestBody Exam exam) {
    try {
      mainMapper.deleteExam(exam.getExamid());
      mainMapper.addExam(exam);
      return new NormalRes(true, "");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new NormalRes(false, "服务器错误");
    }
  }

  @DeleteMapping("/delete-exam")
  public NormalRes DeleteExamRouting(@RequestParam("examid") String examid) {
    try {
      mainMapper.deleteExam(examid);
      return new NormalRes(true, "");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new NormalRes(false, "服务器错误");
    }
  }

  @GetMapping("/solution")
  public Solution SolutionRouting(@RequestParam("userid") String userid,
      @RequestParam("examid") String examid) {
    try {
      return mainMapper.getSolutionByUserAndExam(examid, userid);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @PostMapping("/submit-solution")
  public NormalRes SubmitSolutionRouting(@RequestBody Solution solution) {
    try {
      mainMapper.addSolution(solution);
      return new NormalRes(true, "");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new NormalRes(false, "服务器错误");
    }
  }

  @GetMapping("/solution-list")
  public List<Solution> SolutionListRouting(@RequestParam("examid") String examid) {
    try {
      return mainMapper.getSolutionByExamid(examid);
    } catch (Exception ex) {
      ex.printStackTrace();
      return new ArrayList<>();
    }
  }

  @PostMapping("/new-score")
  public NormalRes NewScoreRouting(@RequestBody Score score) {
    try {
      mainMapper.addScore(score);
      return new NormalRes(true, "");
    } catch (Exception ex) {
      ex.printStackTrace();
      return new NormalRes(false, "服务器错误");
    }
  }

  @GetMapping("/score")
  public Score ScoreRouting(@RequestParam("solutionid") String id) {
    try {
      Score score = mainMapper.getScoreByScoreid(id);
      System.out.println(score);
      return score;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }


}
