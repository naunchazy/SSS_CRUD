day13-ssh工程练习的基本功能：
在maven基础上整合springmvc、spring、springjdbc实现页面登录用户和用户的信息的展示，并实现CRUD
若登录成功，则显示出用户名；若登录失败，则显示用户名和密码，以便用户检查。


SSS工程创建步骤：
	1. 创建maven工程（目录结构，web.xml,Navigatro-facet-3.1） 
	2. 添加工程依赖（参照以前工程 Spring SpringMVC SpringJDBC MYSQL JACKSON JSP JUNIT）
	3. 添加并修改SSS的配置文件（spring-dao.xml spring-service.xml spring-mvc.xml）
		a. spring-dao.xml
			i. 配置扫描注解类
			ii. 读取配置文件db.properties
			iii. 配置数据源ComboPooledDataSource
			iv. 配置JdbcTemplate
		b. spring-service.xml
			i. 配置扫描注解类
			ii. 配置事务管理器
			iii. 配置注解驱动<tx:annotation-driven>
		c. spring-mvc.xml
			i. 配置扫描mvc注解类
			ii. 配置mvc注解驱动
			iii. 配置视图解析器
			iv. 配置静态资源放行
	4. 配置web.xml：
		a. contextConfigListener
		b. ContextLoaderListener
		c. CharacterEncodingFilter（一定放在核心分发器之前，建议放在所有Filter之前）
		d. HiddenHttpMethodFilter
		e. DispatherServlet：init-param、url-pattern
		【handlerMapping、handlerM。。源码自己看看】
	5. 创建实体类
		 PO跟数据库表中的字段对应
	6. 创建Dao
		a. 接口
		b. 实现类
		c. 实现类中的异常能处理掉最好，不能处理往上层抛出
		service实现类要将异常全部处理掉，所有异常要写入日志，有据可循
