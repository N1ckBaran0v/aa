# PHONY target
.PHONY: clean

# Move report
ready/report.pdf: report/report.pdf
	mkdir -p ready
	cp $< $@
	
# move main.py
ready/app-cli-debug: src/main.py ready/levenshtein.py 
	mkdir -p ready
	echo "python3 src/main.py" > $@
	chmod 777 $@

ready/levenshtein.py: src/levenshtein.py
	mkdir -p ready
	cp $< $@
	
# My tests
ready/stud-unit-test-report-prev.json: report/stud-unit-test-report-prev.json
	mkdir -p ready
	cp $< $@
	
# Test app
ready/stud-unit-test-report.json: src/test.py src/levenshtein.py 
	mkdir -p ready
	python3 src/test.py

# clean
clean:
	rm -rf ready
