## Hooks执行顺序

```typescript
import React, { useEffect, useRef } from '@alipay/bigfish/react';
export function useTitle(title: string) {
    const prevTitleRef = useRef(document.title);
    console.log(1)
    useEffect(() => {
        console.log(3)
      document.title = title;
      return () => {
        document.title = prevTitleRef.current;
        console.log(5)
      };
    }, [title]);
  }

const ModuleTest: React.FC = (props) => {
    useTitle('123123');
    useEffect(() => {
       console.log(4)
    }, []);

    return (
        <div>Mouse Pos: {console.log(2)}</div>
    );
}
export default ModuleTest;
```

所有useState => render() => 所有useEffect => 所有useEffect的return。



### 父组件刷新防止子组件无效刷新

1. shouldComponentUpdate

   适用于类组件

   

2. PureComponent

   - 适用于类组件
   -  只做浅层比较

   

3. React.useMemo

   - 适用于函数式组件
   - 只做浅层比较

https://zhuanlan.zhihu.com/p/268802571

```react
const memoBugCharts = React.useMemo(() => <BugCharts chartData={chartData} />, [chartData]);
...

return (
	<div>
    	{memoBugCharts}
    </div>
)
```

```react
import React from "react";

function Child(props) {
  console.log(props.name)
  return <h1>{props.name}</h1>
}

export default React.memo(Child)//props不变的话，组件不会重新渲染，类似于PureComponent

***********************************************
    
function MyComponent(props) {
  /* 使用 props 渲染 */
}
function areEqual(prevProps, nextProps) {
  /*
  如果把 nextProps 传入 render 方法的返回结果与
  将 prevProps 传入 render 方法的返回结果一致则返回 true，
  否则返回 false
  */
}
export default React.memo(MyComponent, areEqual);//自定义比较方式
```

