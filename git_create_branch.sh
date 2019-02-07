for (( i = 0; i < 10; i++ )); do
	git checkout -b dev_${i};
	git push origin dev_${i};
	#statements
done