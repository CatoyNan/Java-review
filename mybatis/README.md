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

