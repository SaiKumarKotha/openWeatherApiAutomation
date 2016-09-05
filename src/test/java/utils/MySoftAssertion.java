package utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class MySoftAssertion {
 private SoftAssert softAssert;
 private MyReporter reporter;

 public MySoftAssertion(MyReporter reporter) {
  softAssert = new SoftAssert();
  this.reporter = reporter;
 }

 public void onAssertSuccess(IAssert assertCommand) {
  // TODO Auto-generated method stub
  softAssert.onAssertSuccess(assertCommand);
 }

 public void onAssertFailure(IAssert assertCommand) {
  // TODO Auto-generated method stub
  softAssert.onAssertFailure(assertCommand);
 }
 

 public void onBeforeAssert(IAssert assertCommand) {
  // TODO Auto-generated method stub
  softAssert.onBeforeAssert(assertCommand);
 }

 public void onAfterAssert(IAssert assertCommand) {
  // TODO Auto-generated method stub
  softAssert.onAfterAssert(assertCommand);
 }
 
 public void assertAll(){
  softAssert.assertAll();
 }

 public void assertTrue(boolean condition, String message) {
  if(condition){
   reporter.verificationStep(message,
     VerificationResult.PASSED);
  } else {
   reporter.verificationStep(message,
     VerificationResult.FAILED);
  } 
  softAssert.assertTrue(condition, message);
 }
 

 public void assertFalse(boolean condition, String message) {
  if(condition){
   reporter.verificationStep(message,
     VerificationResult.FAILED);
  } else {
   reporter.verificationStep(message,
     VerificationResult.PASSED);
  }  
  softAssert.assertFalse(condition, message);
 }

 public void assertFalse(boolean condition) {
  // TODO Auto-generated method stub
  softAssert.assertFalse(condition);
 }

 public void fail(String message, Throwable realCause) {
  // TODO Auto-generated method stub
  softAssert.fail(message, realCause);
 }

 public void fail(String message) {
  // TODO Auto-generated method stub
  softAssert.fail(message);
 }

 public void fail() {
  // TODO Auto-generated method stub
  softAssert.fail();
 }

 public void assertEquals(Object actual, Object expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(Object actual, Object expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(String actual, String expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(String actual, String expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(double actual, double expected, double delta,
   String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, delta, message);
 }

 public void assertEquals(double actual, double expected, double delta) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, delta);
 }

 public void assertEquals(float actual, float expected, float delta,
   String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, delta, message);
 }

 public void assertEquals(float actual, float expected, float delta) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, delta);
 }

 public void assertEquals(long actual, long expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(long actual, long expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(boolean actual, boolean expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(boolean actual, boolean expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(byte actual, byte expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(byte actual, byte expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(char actual, char expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(char actual, char expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(short actual, short expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(short actual, short expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(int actual, int expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(int actual, int expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertNotNull(Object object) {
  // TODO Auto-generated method stub
  softAssert.assertNotNull(object);
 }

 public void assertNotNull(Object object, String message) {
  // TODO Auto-generated method stub
  softAssert.assertNotNull(object, message);
 }

 public void assertNull(Object object) {
  // TODO Auto-generated method stub
  softAssert.assertNull(object);
 }

 public void assertNull(Object object, String message) {
  // TODO Auto-generated method stub
  softAssert.assertNull(object, message);
 }

 public void assertSame(Object actual, Object expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertSame(actual, expected, message);
 }

 public void assertSame(Object actual, Object expected) {
  // TODO Auto-generated method stub
  softAssert.assertSame(actual, expected);
 }

 public void assertNotSame(Object actual, Object expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertNotSame(actual, expected, message);
 }

 public void assertNotSame(Object actual, Object expected) {
  // TODO Auto-generated method stub
  softAssert.assertNotSame(actual, expected);
 }

 public void assertEquals(Collection actual, Collection expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(Collection actual, Collection expected,
   String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(Object[] actual, Object[] expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEqualsNoOrder(Object[] actual, Object[] expected,
   String message) {
  // TODO Auto-generated method stub
  softAssert.assertEqualsNoOrder(actual, expected, message);
 }

 public void assertEquals(Object[] actual, Object[] expected) {
  // TODO Auto-generated method.stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEqualsNoOrder(Object[] actual, Object[] expected) {
  // TODO Auto-generated method stub
  softAssert.assertEqualsNoOrder(actual, expected);
 }

 public void assertEquals(byte[] actual, byte[] expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(byte[] actual, byte[] expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(Set actual, Set expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertEquals(Set actual, Set expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected, message);
 }

 public void assertEquals(Map actual, Map expected) {
  // TODO Auto-generated method stub
  softAssert.assertEquals(actual, expected);
 }

 public void assertNotEquals(Object actual, Object expected, String message) {
  // TODO Auto-generated method stub
  softAssert.assertNotEquals(actual, expected, message);
 }

 public void assertNotEquals(Object actual, Object expected) {
  // TODO Auto-generated method stub
  softAssert.assertNotEquals(actual, expected);
 }

 public void assertNotEquals(float actual, float expected, float delta,
   String message) {
  // TODO Auto-generated method stub
  softAssert.assertNotEquals(actual, expected, delta, message);
 }

 public void assertNotEquals(float actual, float expected, float delta) {
  // TODO Auto-generated method stub
  softAssert.assertNotEquals(actual, expected, delta);
 }

 public void assertNotEquals(double actual, double expected, double delta,
   String message) {
  // TODO Auto-generated method stub
  softAssert.assertNotEquals(actual, expected, delta, message);
 }

 public void assertNotEquals(double actual, double expected, double delta) {
  // TODO Auto-generated method stub
  softAssert.assertNotEquals(actual, expected, delta);
 }

}
