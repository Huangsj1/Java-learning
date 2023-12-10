![[be0c080a00a1515bab410a5566ed590.jpg]]

# 一、请求

1. 类定义前面需要加上 `@RestController` 表示这是请求处理类
	1. `RestController` 包括了 `Controller` 和 `ResponseBody`
2. 里面函数定义前面需要加上 `@RequestMapping("/请求路径")`表示一个功能接口，该函数包括：请求路径、请求参数、响应数据
3. 一般来说请求参数需要和函数形参名一样（或是形参实体对象的属性名一样）

![[c750caa59090c5a929f277ae97bd905.jpg]]

## 1. 简单参数

```java
@RestController  
public class RequestController {  
	// 1.原始方式获取请求参数  
	@RequestMapping("/simpleParam1")  
	public String simpleParam1(HttpServletRequest request){  
	    // 获得请求参数  
	    String name = request.getParameter("name");  
	    String ageStr = request.getParameter("age");  
	  
	    int age = Integer.parseInt(ageStr);  
	    System.out.println(name + ":" + age);  
	    return "OK";  
	}  
	  
	// 2.基于Springboot方式接收  
	//  请求参数名与方法形参变量名相同,不同就需要用@RequestParam来映射
	//   name表示请求的参数名，required表示是否一定需要传 
	@RequestMapping("/simpleParam2")  
	public String simpleParam2(String name, @RequestParam(name = "age", required = false) Integer myage) {  
	    System.out.println(name + ":" + myage);  
	    return "OKK";  
	}
}
```

1. `get` 请求直接在 `Params` 请求行中写参数
2. `post` 请求需要在 `Body` 请求体中写参数（选择 `x-www-form***`）

![[Pasted image 20231205083237.png|315]]![[Pasted image 20231205083041.png|315]]

## 2. 实体参数

当需要传递的遍历是实体/自定义对象时，类的属性名需要和参数名一样

```java
// 二、实体参数  
// 请求参数名要和实体对象属性名相同  
@RequestMapping("/simplePojo")  
public String simplePojo(User user) {  
    System.out.println(user);  
    return "OOK";  
}

public class User {  
    private String name;  
    private int age;  
    private Address address;
    ***
}

public class Address {  
    private String province;  
    private String city;
    ***
}
```

![[Pasted image 20231205084901.png]]

## 3. 数组集合参数

### 1. 数组

所有参数名都需要和数组名一样

```java
// 三、数组集合参数  
// 1.数组参数  
// 所有参数名都需要是数组名  
@RequestMapping("/arrayParam")  
public String arrayParam(String[] hobby) {  
    System.out.println(Arrays.toString(hobby));  
    return "OKOK";  
}
```

![[Pasted image 20231205085452.png]]

### 2. 集合

1. 所有参数名都需要和集合名一样
2. 函数的集合参数前面要加 `@RequestParam`

```java
// 2.集合参数  
@RequestMapping("/listParam")  
public String listParam(@RequestParam List<String> hobby) {  
    System.out.println(hobby);  
    return "okok";  
}
```

![[Pasted image 20231205090049.png]]

## 4. 日期参数

1. 需要指定格式 `@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime`
2. 每个单位的前面位数不足需要补零 

```java
// 四、日期参数  
// 需要指定格式,且每个单位的前面位数不足需要补零  
@RequestMapping("/dateParam")  
public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime) {  
    System.out.println(updateTime);  
    return "OK";  
}
```

![[Pasted image 20231205091227.png]]

## 5. JSON参数

1. 需要放在`post`请求的请求体`Body`中（选择 `raw`，且后面选`JSON`格式）
2. 一般通过实体对象来接收，且JSON的键名需要和对象属性名一样
3. 函数的参数前面需要加上 `@RequestBody`

```java
// 五、JSON参数  
@RequestMapping("/jsonParam")  
public String jsonParam(@RequestBody User user) {  
    System.out.println(user);  
    return "OKK";  
}
```

![[Pasted image 20231205092050.png]]

## 6. 路径参数

1. 请求路径后面可能还有参数 `/**`，但是在函数中不能够写死后面的参数，可以用 `/{id}` 来绑定函数传入的参数 `@PathVariable Integer id`

```java
// 六、路径参数  
@RequestMapping("/pathParam/{id}/{name}")  
public String pathParam(@PathVariable Integer id, @PathVariable String name) {  
    System.out.println(id + ":" + name);  
    return "OK";  
}
```

![[Pasted image 20231205092743.png]]

# 二、响应

1. 类定义前面需要加 `@ResponseBody` （包括在了 `@restController`中）
2. 功能接口的返回值就是响应结果，会转化为 JSON 格式
3. 为了统一响应结果，将返回值统一用定义好的 `Result` 结构来表示

```java
@RequestMapping("/respAddr")  
public Result getAddr() {  
    Address addr = new Address();  
    addr.setProvince("广东");  
    addr.setCity("广州");  
    System.out.println("get Addr");  
    return Result.success(addr);  
}

  
public class Result {  
    private Integer code;   // 1成功，0失败  
    private String msg;     // 提示信息  
    private Object obj;     // 数据date  
  
    public Result() {}  
    public Result(Integer code, String msg, Object obj) {  
        this.code = code;  
        this.msg = msg;  
        this.obj = obj;  
    }  
  
    public Integer getCode() { return code; }  
    public void setCode(Integer code) { this.code = code; }  
    public String getMsg() { return msg; }  
    public void setMsg(String msg) { this.msg = msg; }  
    public Object getObj() { return obj; }  
    public void setObj(Object obj) { this.obj = obj; }  
  
    public static Result success() {  
        return new Result(1, "success", null);  
    }  
    public static Result success(String msg) {  
        return new Result(1, msg, null);  
    }  
    public static Result success(Object data) {  
        return new Result(1, "success", data);  
    }  
  
    public String toString() {  
        return "Result{code = " + code + ", msg = " + msg + ", obj = " + obj + "}";  
    }  
}
```

![[Pasted image 20231205100022.png]]