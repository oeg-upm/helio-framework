# 1. Install the helio-framework dependency in your local repository using this script
mvn clean install -DskipTests
mvn install:install-file -Dfile=./target/framework-0.3.7.jar -DgroupId=upm.oeg.helio -DartifactId=framework -Dversion=0.3.7 -Dpackaging=jar

# 2. In the pom of your project import the follwing dependencies
#
#	<!-- Helio framework -->
#	  	<dependency>
#			<groupId>oeg.helio.framework</groupId>
#		   	<artifactId>helio-framework</artifactId>
#		    <version>0.3.7</version>
#		</dependency>

# 3. You are ready to go and use the framework 