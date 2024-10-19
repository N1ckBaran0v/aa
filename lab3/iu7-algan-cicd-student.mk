# Gradle
GRADLE := gradle/wrapper/gradle-wrapper.jar

# PHONY target
.PHONY: clean

# Move report
ready/report.pdf: docs/report.pdf
	mkdir -p ready
	cp $< $@
	
# Create app (script:))
ready/app-cli-debug: $(GRADLE)
	mkdir -p ready
	./gradlew shadowJar --no-daemon
	echo "java -jar build/libs/lab3.jar \$$@" > $@
	chmod 777 $@
	
# My tests
ready/stud-unit-test-report-prev.json: docs/stud-unit-test-report-prev.json
	mkdir -p ready
	cp $< $@
	
# Test app
ready/stud-unit-test-report.json: $(GRADLE)
	mkdir -p ready
	./gradlew test --no-daemon > scripts/tests.txt 2>&1
	python3 scripts/tests-report.py
	
# Install Gradle	
$(GRADLE): 
	curl https://services.gradle.org/distributions/gradle-8.8-bin.zip -LSso gradle-8.8-bin.zip
	unzip gradle-8.8-bin.zip
	unzip -d tmp gradle-8.8/lib/plugins/gradle-wrapper-8.8.jar 
	cp tmp/gradle-wrapper.jar $(GRADLE)
	rm -rf tmp gradle-8.8 gradle-8.8-bin.zip
	
clean:
	rm -rf ready build scripts/tests.txt
