day13-ssh������ϰ�Ļ������ܣ�
��maven����������springmvc��spring��springjdbcʵ��ҳ���¼�û����û�����Ϣ��չʾ����ʵ��CRUD
����¼�ɹ�������ʾ���û���������¼ʧ�ܣ�����ʾ�û��������룬�Ա��û���顣


SSS���̴������裺
	1. ����maven���̣�Ŀ¼�ṹ��web.xml,Navigatro-facet-3.1�� 
	2. ��ӹ���������������ǰ���� Spring SpringMVC SpringJDBC MYSQL JACKSON JSP JUNIT��
	3. ��Ӳ��޸�SSS�������ļ���spring-dao.xml spring-service.xml spring-mvc.xml��
		a. spring-dao.xml
			i. ����ɨ��ע����
			ii. ��ȡ�����ļ�db.properties
			iii. ��������ԴComboPooledDataSource
			iv. ����JdbcTemplate
		b. spring-service.xml
			i. ����ɨ��ע����
			ii. �������������
			iii. ����ע������<tx:annotation-driven>
		c. spring-mvc.xml
			i. ����ɨ��mvcע����
			ii. ����mvcע������
			iii. ������ͼ������
			iv. ���þ�̬��Դ����
	4. ����web.xml��
		a. contextConfigListener
		b. ContextLoaderListener
		c. CharacterEncodingFilter��һ�����ں��ķַ���֮ǰ�������������Filter֮ǰ��
		d. HiddenHttpMethodFilter
		e. DispatherServlet��init-param��url-pattern
		��handlerMapping��handlerM����Դ���Լ�������
	5. ����ʵ����
		 PO�����ݿ���е��ֶζ�Ӧ
	6. ����Dao
		a. �ӿ�
		b. ʵ����
		c. ʵ�����е��쳣�ܴ������ã����ܴ������ϲ��׳�
		serviceʵ����Ҫ���쳣ȫ��������������쳣Ҫд����־���оݿ�ѭ
