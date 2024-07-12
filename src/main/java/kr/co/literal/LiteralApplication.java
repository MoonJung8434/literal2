package kr.co.literal;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
@MapperScan("kr.co.literal")
public class LiteralApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteralApplication.class, args);
	}

	//MyBatis Framework 관련 환경 설정
	//※ 참조 spring08_mybatis프로젝트 MemMainTest 클래스
	//Mapper 객체 생성
	@Bean //<-스프링 컨테이너(톰캣)가 자동으로 객체 생성
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	    System.out.println("-----sqlSessionFactory() 호출됨");
	    SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
	    bean.setDataSource(dataSource);
	    Resource[] res=new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml");
        bean.setMapperLocations(res);
        return bean.getObject();
	}//sqlSessionFactory() end
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory factory) {
	       System.out.println("-----sqlSession() 호출됨");
	        return new SqlSessionTemplate(factory);
	}//sqlSession() end
}