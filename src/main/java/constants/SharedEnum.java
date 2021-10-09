package constants;

public class SharedEnum {

    public enum ENVIRONMENT {
        QA(""),
        DEV(""),
        DEMO("https://opensource-demo.orangehrmlive.com/");

        private String URL;
        ENVIRONMENT(String URL){
            this.URL = URL;
        }
        public String getURL(){
            return this.URL;
        }
    }

    public enum BROWSER {
        CHROME, FIREFOX, EDGE;
    }
}
