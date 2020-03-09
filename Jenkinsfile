node {
    // reference to maven
    // ** NOTE: This 'maven-3.5.3' Maven tool must be configured in the Jenkins Global Configuration.   
    def mvnHome = tool 'Maven 3.5.3'

    // holds reference to docker image
    def dockerImage
    // ip address of the docker private repository(nexus)
    
    def dockerRepoUrl = "localhost:890"
    def dockerImageName = "api-rest"
    def dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:latest"
    
    stage('Clone Repo') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/Lastsoulk/springboot18.git'
      // Get the Maven tool.
      // ** NOTE: This 'Maven 3.5.3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'Maven 3.5.3'
    }    
  
    stage('Build') {
            sh 'mvn clean install'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
        }
	
	stage('Publish Tests Results'){
      parallel(
        publishJunitTestsResultsToJenkins: {
          echo "Publish junit Tests Results"
		  junit '**/target/surefire-reports/TEST-*.xml'
		  archive 'target/*.jar'
        },
        publishJunitTestsResultsToSonar: {
          echo "This is branch b"
      })
    }
	stage('Image') {
            dir ('account-service') {
                def app = docker.build "localhost:5000/account-service:${env.version}"
                app.push()
            }
        }	
    stage('Build Docker Image') {
      // build docker image
      bat  "whoami"
      bat  "ls -all /var/run/docker.sock"
      bat  "mv ./target/hello*.jar ./data" 
      
      dockerImage = docker.build("api-rest")
    }
   
    stage('Deploy Docker Image'){
      
      // deploy docker image to nexus

      echo "Docker Image Tag Name: ${dockerImageTag}"

      bat  "docker login -u lastsoulk -p kain789741 ${dockerRepoUrl}"
      bat  "docker tag ${dockerImageName} ${dockerImageTag}"
      bat  "docker push ${dockerImageTag}"
    }
}
