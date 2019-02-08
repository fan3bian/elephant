#!/bin/sh
git fetch origin;
origin_branch_list=`git branch -r|grep "dev_"`;
del_ts=`date -d '-3 months' +%s `;
# echo "del_ts:$del_ts";
del_time=`echo "$del_ts" | awk '{print strftime ("%F %T",$0)}'`
echo -e "deadline:$del_time\n"
declare -a delete_list;
seq=0;
for i in $origin_branch_list; do
	last_commit_ts=`git show --pretty=format:"	%ct" $i | head -1`;
	last_commit_time=`echo "$last_commit_ts" | awk '{print strftime ("%F %T",$0)}'`;
	echo $i" "$last_commit_time;
	# echo $del_ts;
	# echo $last_commit_ts;
	if [ $del_ts -gt $last_commit_ts ]; then
		# echo "will be removed";
		# echo $seq;
		delete_list[$seq]=$i;
		((seq=seq + 1));
		# echo $seq;
	fi
done
echo -e "\n"
echo "${delete_list[*]} will be removed"
echo ${delete_list[*]} | sed 's/origin\///g' | sed 's/ /\n/g' | xargs -I {} git push origin :{}

# echo $origin_branch_list;