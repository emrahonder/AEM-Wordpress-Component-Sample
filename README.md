# AEM Wordpress Component Sample
This component is created to show how to trigger a 3rd party REST API and display result of this call on a Adobe Experience Manager 6.4 site.

For show case, Wordpress API is selected. because of popularity of Wordpress. Also almost all companies have own blogs based on Wordpress. And generally they want to display their last 5-10 blog posts on their offical web site etc.

Simply on dialog targetUrl, content type (post or page) and item count are asked. Then according to these information, Wordpress REST API is invoked. In case of failure, just a warning message is prompted otherwise, content details are displayed.

This sample covers REST API call, simple dialog structure, sly usage ont HTL and date formating examples.
