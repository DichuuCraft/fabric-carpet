# Fabric Carpet

DichuuCraft服务器使用的~~trivially~~修改过的Carpet mod.

# 新增的设置
## bedrockBreakingDetectRadius
破基岩玩家检测范围。当有基岩被移除时会在一定范围内搜索最近的玩家并增加基岩挖掘的统计量。
* 类型：`int`
* 默认：`-1`（关闭检测）

## fakesNoPlayerCount
假人（任何非`ServerPlayerEntity`实例的玩家实体）不计入玩家数。
* 类型：`boolean`
* 默认：`false`

## opaCommandLevel
`/opa`命令获取的OP等级。
* 类型：`int`
* 默认：`-1`（关闭`/opa`命令）

## voxelMapWorldName
VoxelMap世界名称。
在多世界服务器中可防止切换世界或进出地狱门时VoxelMap串地图以及总是提示“未知世界”。
* 类型：`String`
* 默认：``（关闭发送世界名数据包）


另外还修复了`mergeTNT`功能（参考[HIT-Carpet](https://github.com/HIT-Craft/HIT-Carpet)）。