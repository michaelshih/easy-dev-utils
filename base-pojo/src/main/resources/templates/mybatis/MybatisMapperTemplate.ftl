<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${MYBATIS_INTEFACE_MODEL.fullClassName}">

    <resultMap id="BASE-RESULTMAP" type="${MYBATIS_XML_MODEL.javaModel.fullClassName}">
        <#list MYBATIS_XML_MODEL.javaModel.fileds as filed>
            <result column="${filed.originName}" property="${filed.javaName}" javaType="${filed.javaType}" jdbcType="${filed.originType}"/>
        </#list>
    </resultMap>

    <sql id="BASE-ALLCOLS">
        ${MYBATIS_XML_MODEL.allColumns}
    </sql>

    <select id="findById" resultMap="BASE-RESULTMAP">
        SELECT
        <include refid="BASE-ALLCOLS"/>
        FROM ${MYBATIS_XML_MODEL.javaModel.originTableName}
        WHERE id = ${r'#{id}'}
    </select>

    <select id="queryByIds" resultMap="BASE-RESULTMAP">
        SELECT
        <include refid="BASE-ALLCOLS"/>
        FROM ${MYBATIS_XML_MODEL.javaModel.originTableName}
        WHERE id IN
        <foreach collection="list" item="item" open="(" close=")" separator=",">
            ${r'#{item}'}
        </foreach>
    </select>

    <insert id="create"
            parameterType="${MYBATIS_XML_MODEL.javaModel.fullClassName}"
            useGeneratedKeys="true" keyColumn="id" keyProperty="id">
        INSERT INTO ${MYBATIS_XML_MODEL.javaModel.originTableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list MYBATIS_XML_MODEL.javaModel.fileds as filed>
                <if test="${filed.javaName} != null">
                    ${filed.originName},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list MYBATIS_XML_MODEL.javaModel.fileds as filed>
                <if test="${filed.javaName} != null">
                    ${r'#{'}${filed.javaName}${r'}'},
                </if>
            </#list>
        </trim>
    </insert>

    <update id="update"
            parameterType="${MYBATIS_XML_MODEL.javaModel.fullClassName}">
        UPDATE ${MYBATIS_XML_MODEL.javaModel.originTableName}
        <set>
              <#list MYBATIS_XML_MODEL.javaModel.fileds as filed>
                  <if test="${filed.javaName} != null">
                      ${filed.originName} = ${r'#{'}${filed.javaName}${r'}'},
                  </if>
              </#list>
        </set>
        WHERE id = ${r'#{id}'}
    </update>
</mapper>