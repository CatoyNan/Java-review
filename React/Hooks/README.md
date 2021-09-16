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