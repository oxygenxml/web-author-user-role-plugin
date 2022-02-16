package com.oxygenxml.webapp.userrole;

import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.Attributes;

import lombok.extern.slf4j.Slf4j;
import ro.sync.basic.util.URLUtil;
import ro.sync.ecss.extensions.api.DocumentTypeCustomRuleMatcher;
import ro.sync.ecss.extensions.api.webapp.SessionStore;
import ro.sync.ecss.extensions.api.webapp.access.WebappPluginWorkspace;
import ro.sync.exml.workspace.api.PluginWorkspaceProvider;

/**
 * Framework matcher based on user role.
 * 
 * @author cristi_talau
 */
@Slf4j
public class UserRuleFrameworkMatcher implements DocumentTypeCustomRuleMatcher {

  /**
   * The role to match.
   */
  private String role;

  /**
   * Constructor.
   * 
   * @param role The role to match.
   */
  public UserRuleFrameworkMatcher(String role) {
    this.role = role;
  }
  
  public boolean matches(String systemID, String rootNamespace, String rootLocalName, String doctypePublicID,
      Attributes rootAttributes) {
    WebappPluginWorkspace webappPluginWorkspace = (WebappPluginWorkspace)PluginWorkspaceProvider.getPluginWorkspace();
    SessionStore sessionStore = webappPluginWorkspace.getSessionStore();
    boolean match = false;
    String sessionId;
    try {
      sessionId = new URL(systemID).getUserInfo();
      if (sessionId != null) {
        String currentRole = sessionStore.get(sessionId, UserRoleSetterExtension.USER_ROLE_OPTION_NAME);
        match = role.equals(currentRole);
      }
    } catch (MalformedURLException e) {
      log.warn("Could match user info for URL: " + URLUtil.clearUserInfo(systemID));
    }
    return match;
  }
  public String getDescription() {
    return "Rule matcher based on user role";
  }

}
