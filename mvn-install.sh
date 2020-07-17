# 1. Install the helio-framework dependency in your local repository using this script
mvn clean install -DskipTests
mvn install:install-file -Dfile=./target/helio-framework-0.2.5.jar -DgroupId=oeg.helio.framework -DartifactId=helio-framework -Dversion=0.2.5 -Dpackaging=jar

# 2. In the pom of your project import the follwing dependencies
#
#	<!-- Helio framework -->
#	  	<dependency>
#			<groupId>oeg.helio.framework</groupId>
#		   	<artifactId>helio-framework</artifactId>
#		    <version>0.2.0</version>
#		</dependency>
#		<!-- Jena basic libraries (all in one): https://mvnrepository.com/artifact/org.apache.jena/apache-jena-libs -->
#     	<dependency>
#            <groupId>org.apache.jena</groupId>
#            <artifactId>apache-jena-libs</artifactId>
#            <version>3.6.0</version>
#            <type>pom</type>
#        </dependency>
#    		<!-- CQEngine -->
#		<dependency>
#		    <groupId>com.googlecode.cqengine</groupId>
#		    <artifactId>cqengine</artifactId>
#		    <version>2.12.5</version>
#		</dependency>


# 3. You are ready to go and use the framework 