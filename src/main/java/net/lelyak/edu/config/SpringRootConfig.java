package net.lelyak.edu.config;

/*
@ComponentScan({"net.lelyak.edu"})
@Configuration
public class SpringRootConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @PostConstruct
    public void startDBManager() {
        //h2
        DatabaseManagerSwing.main(new String[]{"--url", "jdbc:h2:mem:moviedb", "--user", "sa", "--password", ""});
    }

    //MethodInvokingBean example
	*/
/*@PostConstruct
	public void startDBM() {
		MethodInvokingBean mBean = new MethodInvokingBean();

		mBean.setTargetClass(DatabaseManagerSwing.class);
		mBean.setTargetMethod("main");
		String[] args = new String[] { "--url", "jdbc:hsqldb:mem:moviedb", "--user", "sa", "--password", "" };
		mBean.setArguments(args);
		try {
			mBean.prepare();
			mBean.invoke();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*//*


}*/
