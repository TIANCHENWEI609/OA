package mapper;

import org.apache.ibatis.annotations.Mapper;

import pojo.Emp;
@Mapper
public interface EmpMapper {
	//µÇÂ¼·½·¨
    public Emp login(Emp emp);
}