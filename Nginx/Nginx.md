# Nginx

<https://www.cnblogs.com/neromaycry/p/7072872.html>

## nginx proxy_pass 路径多种情况规律总结
undefined：在配置 Nginx 的 `proxy_pass` 指令时，路径可能会变得相当复杂，取决于你是否在 `location` 或 `proxy_pass` 中指定了 URI。以下是常见的场景及其规律总结：

1. 无 URI 更改：
   ```
   location / {
       proxy_pass http://backend;
   }
   ```
   这里，所有请求到 `/` 的请求都被转发到 `http://backend/` 同样的URI路径。

2. 带有斜线的 `proxy_pass`：
   ```
   location / {
       proxy_pass http://backend/;
   }
   ```
   与第一个例子类似，所有请求到根路径 `/` 的请求仍然转发到 `http://backend/`。最后的斜线在这种情况下并没有明显的影响。

3. 替换 URI：
   ```
   location /old/ {
       proxy_pass http://backend/new/;
   }
   ```
   请求到 `/old/some/path` 的请求会被转发到 `http://backend/new/some/path`。这里，`/old/` 部分被 `/new/` 替换。

4. `location` URI 保留：
   ```
   location /old/ {
       proxy_pass http://backend;
   }
   ```
   请求到 `/old/some/path` 的请求将会被转发到 `http://backend/old/some/path`。因为 `proxy_pass` 没有以斜线结束，所以 `location` 中的 `/old/` 路径被保留在转发的 URI 中。

5. 忽略 `location` 的匹配片段：
   ```
   location /old/ {
       proxy_pass http://backend/;
   }
   ```
   请求到 `/old/some/path` 的请求会被转发到 `http://backend/some/path`。通过在 `proxy_pass` 末尾添加斜线 `/`，`/old/` 部分被忽略。

6. 带有非根 URI 的 `proxy_pass`：
   ```
   location / {
       proxy_pass http://backend/nonroot/;
   }
   ```
   所有路径例如 `/any/path` 的请求将被转发到 `http://backend/nonroot/any/path`。

7. 不规则的配置导致错误：
   ```
   location /old/ {
       proxy_pass http://backend/new;
   }
   ```
   这种情况下可能会导致问题，因为 Nginx 在处理斜线时可能不符合预期。它试图将 `location` 中的 `/old/` 映射到 `proxy_pass` 中的 `/new`，但由于 `proxy_pass` 遇到了没有斜线的 `new`，请求可能无法解析到正确的 `backend` URI。


## 这么多情况太难记忆了，没有规律
undefined：确实，`proxy_pass` 的行为乍看起来可能显得复杂，但这里有两个基本的规则可以帮助你记忆和理解：

1. 结尾斜杠挪用规则：
   - 如果 `proxy_pass` 指定的 URL **末尾没有斜杠**，那么 Nginx 会将完整的 `location` 匹配部分传递给 `proxy_pass` 指定的 URL。
   - 如果 `proxy_pass` 指定的 URL **末尾有斜杠**，那么 `location` 匹配部分会被剔除，不会传递给 `proxy_pass` 指定的 URL。

2. URI 替换规则：
   - 如果 `location` 后面跟着是精确匹配（如 = /some_path），那么请求的 URI 将会被 `proxy_pass` 中的 URI 替换。
   - 如果 `location` 后面是正则表达式匹配或普通的前缀匹配，如 `/some_path/`，且 `proxy_pass` 中也指定了 URI (如 `http://backend/other_path/`)，则会进行一对一的替换。

以一个例子来说明这个挪用规则：

- 当配置为：
  ```
  location /foo/ {
      proxy_pass http://backend/;
  }
  ```
  对于请求 `/foo/bar`，`/foo/` 部分将不会传递给后端，最终请求的是 `http://backend/bar`。

- 而这个配置：
  ```
  location /foo/ {
      proxy_pass http://backend;
  }
  ```
  对于同样的请求 `/foo/bar`，会将 `/foo/bar` 完整地传递到后端，最终请求的是 `http://backend/foo/bar`。

记住这两个核心规则之后，理解 `proxy_pass` 的其他特定场景就更为容易了。对于不常见的或复杂的配置，始终最好参考官方的 Nginx 文档以确保准确无误。在实际部署之前，使用测试环境进行验证配置的行为也非常重要。
## 那 root 和 alias 也是这个规律吗
undefined：`root` 和 `alias` 指令在 Nginx 中的行为和 `proxy_pass` 是有区别的，但它们确实也有自己的规则：

1. `root` 指令：
   - 用于指定一个文件资源的根目录（document root），作为为请求提供服务的本地文件系统的起始点。
   - 当使用 `root` 指令时，请求的 URI 完整地接在 `root` 指令指定的路径后面。

   例如：
   ```nginx
   location /images/ {
       root /data;
   }
   ```
   对于请求 `/images/logo.png`，Nginx 将查找 `/data/images/logo.png` 文件。

2. `alias` 指令：
   - 与 `root` 类似，`alias` 用于指定路径，但它会替换掉 `location` 中指定的路径部分，而不是简单地追加。
   - `alias` 后面的路径不会加上 `location` 的路径。

   例如：
   ```nginx
   location /images/ {
       alias /data/photos/;
   }
   ```
   对于请求 `/images/logo.png`，Nginx 将查找 `/data/photos/logo.png` 文件，`/images/` 被替换为了 `/data/photos/`。

和 `proxy_pass` 不同的是，对于 `root` 和 `alias`，斜线（末尾的斜杠）通常更为直观：

- `root` 没有关于斜线的特殊考虑，因为它只是设置了本地文件系统的一个基准点，请求的 URI 会直接追加到这个路径后面。
- `alias` 同样需要注意斜线，因为它会替换 `location` 匹配的部分。如果你的 `location` 末尾有斜线，为保持一致性，最好让 `alias` 路径也以斜线结束。

重要的是要清楚这两个指令的不同用法和行为，理解它们是如何匹配和修改请求的 URI 的。对于不确定的配置，建议设置一个实验环境来测试确保它们的行为符合你的期望。
## alias 需要如何注意斜线，有哪些坑
undefined：在 Nginx 配置中使用 `alias` 指令时，必须注意路径和斜杠（/）的使用方式，因为它们可能会导致预期之外的行为。这里有一些需要注意的“坑”：

1. **斜杠在 `alias` 中的作用**：
   - 当 `location` 使用了尾随斜杠（也就是以 `/` 结尾），`alias` 指令中的路径最好也以斜杠结尾以保持一致。这样做是为了直接替换 `location` 中的路径。

   例子：
   ```nginx
   location /images/ {
       alias /data/photos/;
   }
   ```  
   请求 `/images/logo.png` 会被正确映射到 `/data/photos/logo.png`。

   但如果我们将 `alias` 路径中的斜线去掉：
   ```nginx
   location /images/ {
       alias /data/photos;
   }
   ```
   则请求 `/images/logo.png` 可能会错误地映射到 `/data/photoslogo.png`，注意这里 `photos` 和 `logo.png` 之间缺少斜杠。

2. **没有尾随斜杠时的潜在问题**：
   如果没有在 `location` 或 `alias` 指令中使用尾随斜杠，路径可能不会按预期地被替换或追加。

   例如：
   ```nginx
   location /images {
       alias /data/photos;
   }
   ```
   这样的配置可能会导致 `/imageslogo.png` 请求被映射到 `/data/photoslogo.png`，同样地，缺少了斜杠。

3. **`alias` 与正则表达式**:
   当使用正则表达式的 `location` 匹配时，尤其要注意 `alias` 的路径问题。

   例子：
   ```nginx
   location ~ ^/images/(.*)$ {
       alias /data/photos/$1;
   }
   ```
   在这种情况下，`$1` 会代表正则表达式中括号 `(.*)` 匹配的部分。然而，如果正则表达式没有准确地设计好，或者 `alias` 在路径拼接时没有正确地使用，那么最终的文件路径也可能不正确。

