Setenv Builder plugin
=====================

Japanese version of this document is README_ja.md

Jenkins plugin to add a build-step to define new environment variables that can be used in following steps.
What's this?
------------

Setenv Builder is a [Jenkins](http://jenkins-ci.org/) plugin.
This plugin provides Define variables build step:

* It defines new variables, that can be referenced in the following build steps.
* You can select the way to specify variables:
	* With List: Specify variables with specifying each name and variable in a text field.
	* With Textarea: Specify variables with "NAME=VALUE" format in each line in a textarea.
* Additional way to specify variables can be extended by using [the Jenkins extention point featere] (https://wiki.jenkins-ci.org/display/JENKINS/Extension+points).

Limitations
-----------

* Build parameters cannot be overwritten.

Extension point
---------------

A new additional way to specify variables be added with extending `SetenvConfig`, overriding the following method:

```java
abstract public Map<String, String>  SetenvConfig::getEnvMap(EnvVars env, PrintStream logger);
```

Or, you can extend `AbstractSetenvConfig`, and define name and value pairs by calling following method:

```java
public void AbstractSetenvConfig::setSetenvEntryList(List<SetenvEntry> setenvEntryList);
```

TODO
----

* Write tests.
* Write comments in English (that is, Englishize)
* [Releasing a Plugin and Hosting a Plugin on jenkins-ci.org] (https://wiki.jenkins-ci.org/display/JENKINS/Hosting+Plugins)
