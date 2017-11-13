# Auto

>背景：通常的ETL（Extract-Transform-Load），是先抓取数据，再进行清洗，再加载。清洗的时候也可能伴随着聚合运算。其中该项目是实现Load这一步的，统一Transform的输出为csv|tsv文件，然后将其导入到关系型数据库。

>流程：1.获取文件名信息，命名规则一般是，sales_fact_20171011_d.csv</br>
>　　　2.配置正则可抓取文件名中的日期变量</br>
>　　　3.置前执行：根据文件名中日期变量删除某一天的数据（支持重算）</br>
>　　　4.导入文件：根据配置的SQL对应导入每行csv|tsv的数据（支持重算）</br>
>　　　5.置后执行：根据文件名中日期变量聚合该天数据插入新字段（支持重算）</br>
