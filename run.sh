#!/bin/bash

CLASS_PATH=/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.jdt.core_3.9.1.v20130905-0837.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.core.runtime_3.9.0.v20130326-1255.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.equinox.common_3.6.200.v20130402-1505.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.osgi_3.9.1.v20130814-1242.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.core.resources_3.8.101.v20130717-0806.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.core.jobs_3.5.300.v20130429-1813.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.core.contenttype_3.4.200.v20130326-1255.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.equinox.preferences_3.5.100.v20130422-1538.jar:/opt/eclipse-sdk-bin-4.3/plugins/org.eclipse.text_3.5.300.v20130515-1451.jar:build

if [[ "$0" == *"run.sh" ]]; then
    java -cp "$CLASS_PATH" ECLIJavaCodeFormatter $@
elif [[ "$0" == *"compile.sh" ]]; then
    javac -cp "$CLASS_PATH" -d build/ $@
fi
