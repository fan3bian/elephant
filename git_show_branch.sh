#!/bin/sh
origin_branch_list=`git branch -r|grep "dev_"`;
del_ts=`date -d '2 hours' +%s `;
del_time=`echo "$del_ts" | awk '{print strftime ("%F %T",$0)}'`
echo -e "deadline:$del_time\n"
for i in $origin_branch_list; do
	last_commit_ts=`git show --pretty=format:"	%ct" $i | head -1`;
	last_commit_time=`echo "$last_commit_ts" | awk '{print strftime ("%F %T",$0)}'`;
	echo $i" "$last_commit_time;
done

# echo $origin_branch_list;