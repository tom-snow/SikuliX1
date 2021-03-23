/*
 * Copyright (c) 2010-2021, sikuli.org, sikulix.com - MIT license
 */
package org.sikuli.script.runners;

import java.io.File;

import org.sikuli.basics.FileManager;
import org.sikuli.script.runnerSupport.IScriptRunner;
import org.sikuli.script.support.Commons;
import org.sikuli.script.support.RunTime;

public class PowershellRunner extends AbstractLocalFileScriptRunner {

  public static final String NAME = "PowerShell";
  public static final String TYPE = "text/powershell";
  public static final String[] EXTENSIONS = new String[] {"ps1"};

  @Override
  protected int doEvalScript(String script, IScriptRunner.Options options) {
    File aFile = FileManager.createTempFile("ps1");
    FileManager.writeStringToFile(script, aFile);
    return runScript(aFile.getAbsolutePath(), null, null);
  }

  @Override
  protected int doRunScript(String scriptFile, String[] scriptArgs, IScriptRunner.Options options) {
    File fScriptFile = new File(scriptFile);

    String[] psDirect = new String[]{
            "powershell.exe", "-ExecutionPolicy", "UnRestricted",
            "-NonInteractive", "-NoLogo", "-NoProfile", "-WindowStyle", "Hidden",
            "-File", fScriptFile.getAbsolutePath()
    };
    String[] psCmdType = new String[]{
            "cmd.exe", "/S", "/C",
            "type " + fScriptFile.getAbsolutePath() + " | powershell -noprofile -"
    };
    String retVal = RunTime.runcmd(psCmdType);
    String[] parts = retVal.split("\\s");
    int retcode = -1;
    try {
      retcode = Integer.parseInt(parts[0]);
    } catch (Exception ex) {
    }
    if (retcode != 0) {
      log(-1, "PowerShell:\n%s\nreturned:\n%s", fScriptFile, RunTime.getLastCommandResult());
    }
    return retcode;
  }

  @Override
  public boolean isSupported() {
    return Commons.runningWindows();
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String[] getExtensions() {
    return EXTENSIONS.clone();
  }

  @Override
  public String getType() {
    return TYPE;
  }
}
