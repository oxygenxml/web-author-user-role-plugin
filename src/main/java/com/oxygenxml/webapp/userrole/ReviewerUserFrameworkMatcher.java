package com.oxygenxml.webapp.userrole;

/**
 * Rule matcher that matches users with role "sme".
 * 
 * @author cristi_talau
 */
public class ReviewerUserFrameworkMatcher extends UserRuleFrameworkMatcher {

  public ReviewerUserFrameworkMatcher() {
    super("reviewer");
  }

}
