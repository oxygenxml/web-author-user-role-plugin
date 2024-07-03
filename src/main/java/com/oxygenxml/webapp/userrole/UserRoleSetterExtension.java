package com.oxygenxml.webapp.userrole;

import java.net.URL;
import java.util.Map;

import ro.sync.ecss.extensions.api.webapp.SessionStore;
import ro.sync.ecss.extensions.api.webapp.access.EditingSessionOpenVetoException;
import ro.sync.ecss.extensions.api.webapp.access.WebappEditingSessionLifecycleListener;
import ro.sync.ecss.extensions.api.webapp.access.WebappPluginWorkspace;
import ro.sync.exml.plugin.workspace.WorkspaceAccessPluginExtension;
import ro.sync.exml.workspace.api.Platform;
import ro.sync.exml.workspace.api.standalone.StandalonePluginWorkspace;

/**
 * Extension that sets the user role according to an URL parameter set on the first opened document.
 * 
 * @author cristi_talau
 */
public class UserRoleSetterExtension implements WorkspaceAccessPluginExtension{

  /**
   * The option name.
   */
  public static final String USER_ROLE_OPTION_NAME = "user-role";

  /**
   * Alternate name for the option - used in CF.
   */
  public static final String USER_ROLE_OPTION_ALTERNATE_NAME = "userRole";

  public boolean applicationClosing() {
    return false;
  }

  public void applicationStarted(StandalonePluginWorkspace pluginWorkspace) {
    if (pluginWorkspace.getPlatform() == Platform.WEBAPP) {
      final WebappPluginWorkspace webappPluginWorkspace = ((WebappPluginWorkspace)pluginWorkspace);
      final SessionStore sessionStore = webappPluginWorkspace.getSessionStore();
      webappPluginWorkspace.addEditingSessionLifecycleListener(new WebappEditingSessionLifecycleListener() {
        @Override
        public void editingSessionAboutToBeStarted(String docId, String licenseeId, URL systemId, Map<String, Object> options)
            throws EditingSessionOpenVetoException {
          String sessionId = systemId.getUserInfo();
          if (sessionId != null && sessionStore.get(sessionId, USER_ROLE_OPTION_NAME) == null) {
            String userRole = getUserRole(sessionId, options);
            if (userRole != null) {
              sessionStore.put(sessionId, USER_ROLE_OPTION_NAME, userRole);
            }
          }
        }
      });
    }
  }

  /**
   * Return the user role.
   * 
   * @param sessionId The session ID.
   * @param options The loading options.
   * 
   * @return The user role.
   */
  private String getUserRole(String sessionId, Map<String, Object> options) {
    return (String) options.get(USER_ROLE_OPTION_NAME);
  }
}
