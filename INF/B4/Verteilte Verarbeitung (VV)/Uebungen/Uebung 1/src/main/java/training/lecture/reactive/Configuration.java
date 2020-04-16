package training.lecture.reactive;

public class Configuration
{
    public static void main(String[] args) throws Exception
    {
        //Video: 16ConfigurationWithCLI_kompakt

/*        Options options = new Options();
        options.addOption(
                new Option("u", "username", true, "name of user"));
        options.addOption(
                new Option("p", "password", true, "name of user"));

        CommandLineParser parser = new DefaultParser();
        CommandLine       cmd = parser.parse(options, args);

        System.out.println("User = " + cmd.getOptionValue("u"));
        System.out.println("Password = " + cmd.getOptionValue("p"));*/


        //Video: 17ConfigurationProperties_kompakt

/*        Properties properties = new Properties();
        InputStream propFile = Configuration.class
                .getClassLoader()
                .getResourceAsStream("application.properties");
        properties.load(propFile);

        System.out.println("Username: " + properties.getProperty("user.name"));
        System.out.println("Password: " + properties.getProperty("user.password"));*/


        //Video: 18ConfiguratoinEnvironment_kompakt

/*        Map<String, String> environment = System.getenv();
        System.out.println("User = " + environment.get("USER"));
        System.out.println("Password = " + environment.get("PASSWORD"));*/
    }
}
