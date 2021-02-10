package org.sikuli.script.support;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import org.sikuli.script.runnerSupport.JythonSupport;

import java.util.List;

public class SikulixEvaluate {
  public static void main(String[] args) {
    RunTime.get().show();
    if (args.length == 0) {
      Commons.printLog("SikulixEvaluate: Nothing to do!");
      return;
    }
    if ("test".equals(args[0])) {
      test();
    }
  }

  public static void test() {

//    Screen scr = new Screen();

//TEST: find(Image.getSub()) did not work always (BufferedImage problem)
//solution: make sub same type as original
//    Image image = new Image(scr.userCapture());
//    try {
//      scr.find(image).highlight(2);
//      Image subImage = image.getSub(Region.WEST); //
//      scr.find(subImage).highlight(2);
//    } catch (FindFailed findFailed) {
//      Commons.printLog("not found: %s", image);
//    }

//BREAKPOINT after test
    Commons.printLog("***** end of testing *****");

//TEST: macOS S & P behavior
//    new Screen();

//TEST: sim value can be float
//    Settings.MinSimilarity = 0.6f;
//    Pattern pat = new Pattern("someImage");
//    Commons.printLog("%s", pat);
//    pat = new Pattern("someImage").similar(0.6f);
//    Commons.printLog("%s", pat);
  }

  private static void testFolderList() {
    try {
      Class<?> aClass = Class.forName("net.sourceforge.tess4j.Tesseract");
      String folder = "/tessdata";
      aClass = JythonSupport.class;
//      folder = "LibJython";
//      folder = ".";
//      List<String> fileList = Commons.getFileList(folder, aClass);
      List<String> fileList = Commons.getFileList("<appdata>/SikulixLibs");
      fileList = null;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void testReadContent() {
    List<String> contentList = Commons.getContentList("LibJython", JythonSupport.class);
    String log = Commons.getLog();
  }
}
