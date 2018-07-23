package com.rewedigital.gradle.galen.tasks

import com.rewedigital.gradle.galen.util.Util
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

import static com.rewedigital.gradle.galen.GalenPluginExtension.EXTENSION_NAME

class GalenDownloadTask extends DefaultTask {

    private static final Logger LOG = Logging.getLogger(GalenDownloadTask.class)

    @Input
    def getGalenDownloadUrl() {
        "${project.extensions[EXTENSION_NAME].galenDownloadUrl}"
    }

    @OutputFile
    def getDownloadFile() {
        new File("${project.extensions[EXTENSION_NAME].galenCacheDirectory}", "/${project.extensions[EXTENSION_NAME].galenVersion}/galen.zip")
    }

    @TaskAction
    def action() {
        try {
            LOG.quiet("Downloading Galen Framework from {} to '{}' ...", getGalenDownloadUrl(), getDownloadFile().getAbsolutePath())
            Util.download(getGalenDownloadUrl(), getDownloadFile())
            LOG.quiet("DONE.")
        } catch (Exception e) {
            if (project.extensions[EXTENSION_NAME].failBuildOnErrors) {
                LOG.error("Download failed: {}", e.message)
                throw e
            } else {
                LOG.warn("Download failed, nevertheless not failing the Build: {}", e.message)
            }
        }
    }
}
