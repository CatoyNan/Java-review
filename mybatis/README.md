### insert返回主键

使用JDBC方式返回主键自增的值
[80]()

使用selectKey返回主键的值
[83](http://play.google.com/books/reader?printsec=frontcover&output=reader&id=LEOKJgAAAEAJ&source=books-notes-export&pg=GBS.PA83.w.0.0.0.0.1)







## mapper接口是如何映射xml的

#### 1类路径

```xml
<!-- 使用相对于类路径的资源引用 -->
常用，文件名不需要相同，位置无限制
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
```

#### 2全限定类名

```xml
<!-- 使用映射器接口实现类的完全限定类名 -->
xml要和接口在同一个包下，且名称需相同
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
```

#### 3包路径

```xml
<!-- 将包内的映射器接口实现全部注册为映射器 -->
同上
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

这种配置方式会先查找tk.mybatis.simple.mapper包下所有的接口，循环对接口进行如下操作。

- 1.判断接口对应的命名空间是否已经存在，如果存在就抛出异常，不存在就继续进行接下来的操作。
- 2.加载接口对应的 XML 映射文件，将接口全限定名转换为路径，例如，将接口tk.mybatis.simple.mapper.UserMapper转换为tk/mybatis/simple/mapper/UserMapper.xml，以.xml为后缀搜索XML资源，如果找到就解析XML。
- 3.处理接口中的注解方法。
  因为这里的接口和XML映射文件完全符合上面操作的第2点，因此直接配置包名就能自动扫描包下的接口和XML映射文件，省去了很多麻烦。准备好这一切后就可以开始学习具体的用法了。



## 映射XML和接口的命名需要符合如下规则。

- 当只使用XML而不使用接口的时候，namespace的值可以设置为任意不重复的名称。

- 标签的id属性值在任何时候都不能出现英文句号“.”，并且同一个命名空间下不能出现重复的id。

-  因为接口方法是可以重载的，所以接口中可以出现多个同名但参数不同的方法，但是XML中id的值不能重复

  因而接口中的所有同名方法会对应着XML中的同一个id的方法。最常见的用法就是，同名方法中其中一个方法增加一个 RowBound 类型的参数用于实现分页查询。
  

https://www.jianshu.com/p/45b60b7bb98b



### 动态条件查询模版

```xml
<select id="getTransferCompanyBackInfoByCondition" parameterType="java.util.Map" resultMap="TransferCompanyBackInfo">
		select id
		,transfer_company
		,transfer_no
		,transfer_no_time
		,weight
		,transfer_fee
		,transfer_fee_haihu
		,gmt_create
		,gmt_modified
		,remarks
		,order_no
		,country
		,clear_type
		from   transfer_company_back_info
		<where>
			<if test="transferCompany != null and transferCompany != ''">
				and transfer_company = #{transferCompany}
			</if>
			<if test="transferNo != null and transferNo != ''">
				and transfer_no = #{transferNo}
			</if>
			<if test="transferNoTime != null and transferNoTime != ''">
				and transfer_no_time = #{transferNoTime}
			</if>
			<if test="weight != null and weight != ''">
				and weight = #{weight}
			</if>
			<if test="transferFee != null and transferFee != ''">
				and transfer_fee = #{weitransferFeeght}
			</if>
			<if test="transferFeeHaihu != null and transferFeeHaihu != ''">
				and transfer_fee_haihu = #{transferFeeHaihu}
			</if>
			<if test="orderNo != null and orderNo != ''">
				and order_no = #{orderNo}
			</if>
			<if test="country != null and country != ''">
				and country = #{country}
			</if>
			<if test="clearType != null and clearType != ''">
				and clear_type = #{clearType}
			</if>
		</where>
		<if test="sidx != null and sord != null">
			order by gmt_modified desc
		</if>
		<if test="start != null and limit != null">
			limit #{start}, #{limit}
		</if>
	</select>
```

- https://www.diigo.com/outliner/diigo_items/911646/10722170/543794966?key=u0ymza1epq)



### mapper接口动态创建的原理与实现

https://play.google.com/books/reader?printsec=frontcover&output=reader&id=LEOKJgAAAEAJ&source=books-notes-export&pg=GBS.PA99