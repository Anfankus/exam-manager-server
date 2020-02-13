package anfankus;

import anfankus.dao.Exam;
import anfankus.dao.Score;
import anfankus.dao.Solution;
import anfankus.dao.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IMainMapper {
//  @Select("select examid,examno,examname,starttime,examtime,description,totalscore,sqstring,saqstring,userid from exam")
//  List<Exam> getExamList();

  @Results({
      @Result(property = "sqstring", column = "sq"),
      @Result(property = "saqstring", column = "saq")
  })
  @Select("select * from exam")
  List<Exam> getExamMetaList();

  @Results({
      @Result(property = "sqstring", column = "sq"),
      @Result(property = "saqstring", column = "saq")
  })
  @Select("select * from exam where userid=#{userid}")
  List<Exam> getExamMetaListByUserid(String userid);

  @Select("select * from user")
  List<User> getUserList();

  @Select("Select * from solution")
  List<Solution> getSolutionList();

  @Select("select userid,username,usertype,'' as password from user where userid=#{userid}")
  User getUserById2(String id);

  @Select("select * from user where userid=`#{id}`")
  User getUserById(String id);

  @Select("Select * from user where userid=#{userid}")
  User verifyUser(String userid);


  @Results({
      @Result(property = "sqstring", column = "sq"),
      @Result(property = "saqstring", column = "saq")
  })
  @Select("Select * from exam where examid=#{id}")
  Exam getExamById(String id);


  @Results({
      @Result(property = "answerString", column = "answer")
  })
  @Select("Select * from solution where examid=#{examid} and userid=#{userid}")
  Solution getSolutionByUserAndExam(String examid, String userid);


  @Results({
      @Result(property = "answerString", column = "answer")
  })
  @Select("Select * from solution where examid=#{examid}")
  List<Solution> getSolutionByExamid(String examid);


  @Results({
      @Result(property = "valueString", column = "value")
  })
  @Select("Select * from score where solutionid=#{solutionid}")
  Score getScoreByScoreid(String solutionid);

  //Insert
  @Insert("Insert into user values(#{user.userid},#{user.username},#{user.password},#{user.usertype})")
  int addUser(@Param("user") User user);

  @Insert("Insert into exam values("
      + "#{exam.examid},"
      + "#{exam.examno},"
      + "#{exam.examname},"
      + "#{exam.starttime},"
      + "#{exam.examtime},"
      + "#{exam.description},"
      + "#{exam.totalscore},"
      + "#{exam.sqstring},"
      + "#{exam.saqstring},"
      + "#{exam.userid})")
  int addExam(@Param("exam") Exam exam);

  @Insert("Insert into solution values(#{solu.solutionid},#{solu.examid},#{solu.userid},#{solu.submittime},#{solu.answerString},#{solu.totalscore})")
  int addSolution(@Param("solu") Solution solution);

  @Insert("Insert into score values(#{s.scoreid},#{s.solutionid},#{s.valueString})")
  int addScore(@Param("s") Score s);

  @Delete("delete from exam where examid=#{examid}")
  int deleteExam(String examid);

}
