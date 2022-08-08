Web Author User Role Plugin
===========================


This plugin provides building-blocks to customize the user interface accroding to the user role. It is meant for developers as a starting point rather than a ready-to-use plugin.

The user role is passed in as a [loading option](https://www.oxygenxml.com/maven/com/oxygenxml/oxygen-webapp/21.0.0.0/jsdoc/tutorial-loadingoptions.html), or an URL parameter with the name `user-role`. The role is set once the first document in a session is loaded and cannot be changed for that session. The currently recognize user roles are "author", "reviewer" and "sme".

It includes:

- Implementations of `ro.sync.ecss.extensions.api.DocumentTypeCustomRuleMatcher` that match one of the supported user roles. These can be used to activate certain frameworks only for certain users. 
If you want to use Oxygen XML Editor to configure your framework:
  - Open the document type configuration dialog box for your framework.
  - Add the compiled classes of this plugin in the **Classpath** tab.
  - In the `Association rules` tab, select the class that corresponds to the role of the user that the framework applies to.
  
## Installation
To install this plugin, download one of the releases ([link](https://github.com/oxygenxml/web-author-user-role-plugin/releases)) and [upload it in your Web Author deployment](https://www.oxygenxml.com/doc/ug-webauthor/topics/webapp-configure-plugins.html).

Copyright and License
---------------------
Copyright 2019 Syncro Soft SRL.

This project is licensed under [Apache License 2.0](https://github.com/oxygenxml/dita-latex/blob/master/LICENSE).
The plugin contains a Java library provided by the JLatexMath under the GNU General Public License v2.0 w/Classpath exception license: https://github.com/opencollab/jlatexmath/blob/master/LICENSE
