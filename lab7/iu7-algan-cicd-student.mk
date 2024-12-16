# PHONY target
.PHONY: clean

# Move report
ready/report.pdf: report/report.pdf
	mkdir -p ready
	cp $< $@
	
# Clean
clean:
	rm -rf ready 
