The files in the "samples" folder are meant to be used as a starting point 
for your own JSF facelets (i.e., .xhtml) files, to save you from typing in 
the long and tedious DOCTYPE, <html...> declaration, and basic tags.

Please note that the files use the JSF 2.2 format, and are not compatible with
JSF 2.1 and earlier.

The the versions that say "with-comments" have some explanations inside the
files in HTML comments. Most of the time, you probably want to use the
other versions, that do NOT say "with-comments", because you do not want your 
final files to contain the comments. For each (with and without comments), 
there is a version for the case when you are making a form, and another 
version for the case where you have a pure output (results) page that contains
no form. 

You have two main options: copying the files into your own JSF project,
or copying and renaming the entire jsf-blank project as described in
the notes.

A) If you make a new project of your own, start by specifying the JSF 2.2
user library (you have to do this the FIRST time only). Go to Window,
Preferences, Java, Build Path, User Libraries, do Add, name it something
like JSF-2.2-Library, then Add External JARs, then point at your JSF
2.2 JAR file. Then make a Dynamic Web Project, and choose "JavaServer Faces
v 2.2 project". You probably want to remove the default URL mapping and
use *.jsf instead. Then, copy the appropriate .xhtml file 
from jsf-blank/WebContent/samples into the WebContent 
(not the WebContent/samples) folder of your app. Rename the file to
give it a name that is meaningful for your app. 
  Optional: copy web.xml and faces-config.xml from jsf-blank/WebContent/WEB-INF 
into the WEB-INF folder of your new app. In particular, the web.xml made
by the Eclipse wizard is not what you usually want, since it has some
extraneous things and since it omits the Development value for 
javax.faces.PROJECT_STAGE, which is VERY useful during development.
  Optional: also copy the "css" folder from jsf-blank/WebContent/ into 
the WebContent folder of your app. You can omit this step, but then you
won't have a style sheet, and your pages will not look the same as my
examples.

B) If you copy and rename the entire jsf-blank project, first remember that 
you have to patch the .component file as described in the tutorial. Then, copy
sample-file-no-form.xhtml and sample-file-with-form.xhtml into WebContent and 
rename them to have meaningful names. You should delete index.xhtml, page-b.xhtml, 
the samples folder, and the somepackage Java package, so that your project does 
not have extraneous files.

Either way, the samples are simply supposed to save you from typing in the core
.xhtml tags. You still have to write all the Java code as described in the
tutorial.



Taken from http://www.coreservlets.com/JSF-Tutorial/jsf2/ -- 
MANY more details on creating and copying JSF projects there.