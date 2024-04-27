pipeline {
    agent any

    stages {
        stage('Trigger DevJobImulation_G9') {
            steps {
                script {
                    def devJob = build(job: 'DevJobImulation_G9', propagate: false)
                    if (devJob.result == 'SUCCESS') {
                        stage('Trigger Deploing_ENV') {
                            def envJob = build(job: 'Deploing_ENV', propagate: false)
                            if (envJob.result == 'SUCCESS') {
                                stage('Trigger G9_run_test') {
                                    build(job: 'G9_run_test', parameters: [
                                        string(name: 'Module', value: 'overAll'),
                                        string(name: 'testName', value: 'LoginTestWithPageObject#validLogin'),
                                        string(name: 'defaultLogin', value: '-DdefaultLogin=qaauto'),
                                        string(name: 'additionalParameters', value: '-DdefaultPassword=123456qwerty'),
                                        string(name: 'devBuildNumber', value: String.valueOf(devJob.number))
                                    ], quietPeriod: 0, wait: true)
                                }
                            } else {
                                error("Deploing_ENV job failed")
                            }
                        }
                    } else {
                        error("DevJobImulation_G9 job failed")
                    }
                }
            }
        }
    }
}