package org.rewedigital.frost

class GalenPluginExtension {

    static final String EXTENSION_NAME = 'galen'


    String galenWorkingDirectory = 'galen'
    String galenCacheDirectory = "${System.getProperty('user.home')}/.galen"
    String galenVersion = '2.4.0'
    String galenDownloadUrl

    String[] browsers = ['firefox', 'chrome']
    HashMap<String, String> browserImages = [:]
    String testsuitesDirectory = 'src/uiTest/galen/tests'
    boolean recursive = false
    String testGroups
    int numberOfParallelTests = 1

    String sutTag = 'latest'
    int sutPort = 8080
    String sutHealthCheckEndpointPath = '/admin/healthcheck'
    int sutReadinessTimeoutInMinutes = 5

    String composeFile = 'docker-compose.yml'
    String composeOverrideFile = 'docker-compose.override.galen.yml'

    boolean useProxy = false
    String proxyConfigurationDirectory = 'galen'

    boolean failBuildOnErrors = true


    String getActualGalenDownloadUrl() {
        if (galenDownloadUrl?.trim()) {
            return galenDownloadUrl.trim()
        }

        "https://github.com/galenframework/galen/releases/download/galen-${galenVersion}/galen-bin-${galenVersion}.zip "
    }
}