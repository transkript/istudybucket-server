find > stoopid.txt
echo "moasndon" > commit-list.txt
while read p; do
    if [ -d $p ]; then
        echo "is directory"
    else
        echo $p >> commit-list.txt
    fi
done < stoopid.txt

i=0
while read c; do
    if [ $i -le 200 ]; then
        git add $c;
        git commit -m "ek-autocomm: added $c";
    fi
    if [ $i -gt 80 ]; then
        echo $c >> new-commit-list.txt
    fi
    i=$(($i+1))

done < commit-list.txt

cat new-commit-list.txt > commit-list.txt
rm stoopid.txt
rm new-commit-list.txt
i=0
