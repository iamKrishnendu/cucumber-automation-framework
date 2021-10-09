package com.sikuli.test;



import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;


/**
 * <p>
 *     Image based automation with Sikuli<br/>
 *     For reference only<br/>
 *     chances of failure is high. Recommended to capture element snapshots first<br/>
 *     Snapshots can be stores into `elementSnapshots` root folder<br/>
 * </p>
 */
public class LoginScenarioTest{

    @Test
    public void loginWithUsernameAndPasswordSikuli_Test(Method method){
        Screen screen = new Screen();
        Pattern username = new Pattern(System.getProperty("user.dir")+ File.separator+"elementSnapshots"+File.separator+"usernameInput.PNG");
        Pattern password = new Pattern(System.getProperty("user.dir")+ File.separator+"elementSnapshots"+File.separator+"passwordInput.PNG");
        Pattern login = new Pattern(System.getProperty("user.dir")+ File.separator+"elementSnapshots"+File.separator+"loginButton.PNG");

        try{
            screen.wait(username.similar((float) 0.90),5);
            screen.type(username.similar((float) 0.90),"Admin");
            screen.wait(password.similar((float) 0.90),5);
            screen.type(password.similar((float) 0.90),"admin123");
            screen.click(login.similar((float) 0.90));
        }catch (FindFailed e){
            e.printStackTrace();
        }

    }
}
