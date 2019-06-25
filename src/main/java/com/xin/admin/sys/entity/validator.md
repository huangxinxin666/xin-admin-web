Java Bean Validator内置的注解：校验只能对Object的属性进行校验，不能对单个的参数进行校验

@AssertFalse 校验false
@AssertTrue 校验true

@DecimalMax(value=,inclusive=) 小于等于value，inclusive=true,是小于等于
@DecimalMin(value=,inclusive=) 与上类似
@Max(value=) 小于等于value，会给对应的数据库表字段添加一个check的约束条件
@Min(value=) 大于等于value，会给对应的数据库表字段添加一个check的约束条件
@Digits(integer=整数位数, fraction=小数位数) 整数位数和小数位数上限，对应的数据库表字段会被设置精度(precision)和准度(scale)
@Positive 数字，正数
@PositiveOrZero 数字，正数或0
@Negative 数字，负数
@NegativeOrZero 数字，负数或0

@NotNull 不能为null，但可以为empty，对应的表字段不允许为null.
@NotEmpty 不能为null，而且长度必须大于0
@NotBlank 只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
@Null 为null

@Past （日期类型Date）比当前时间早
@PastOrPresent （日期类型Date）比当前时间早或等于当前时间
@Future （日期类型Date）比当前时间晚
@FutureOrPresent （日期类型Date）比当前时间晚或等于当前时间

@Pattern(regex=,flag=)  正则
@Size(min=, max=)  校验对象的size（字符串、集合、map、arrays等），对应的数据库表字段的长度会被设置成约束中定义的最大值
@Email 字符串，邮箱类型
@Valid 对po实体类进行校验,任何非原子类型

自定义的注解
@ValidEnum 校验是否在枚举范围内
@ValidJson 校验JSON不能为null以及JSON格式
@ValidDict 校验是否在数据字典范围内
