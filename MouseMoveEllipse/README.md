# MouseMoveEllipse.java [![](https://img.shields.io/badge/License-AGPL_v3-blue.svg)](https://tldrlegal.com/license/gnu-affero-general-public-license-v3-(agpl-3.0)) <img src="https://img.shields.io/badge/Java-8-brightgreen.svg" title="Ok" alt="Java : Ok"/>

## Disclaimer
I ported the code almost exactly how it was made (at least all the logic), including the comments.

[The original code can be found here](https://github.com/MasterFocus/AutoHotkey/tree/master/Functions/MouseMove_Ellipse).

## Usage
```posX1, posY1 [, paramOptions]```

#### Required Parameters
| Name | Description |
| :--- | :--- |
| posX1, posY1 | Destination coordinates |

#### Optional Parameters
| Name | Description | Default |
| :--- | :--- | :--- |
| paramOptions | String of options (see below) | blank |

The *paramOptions* string may contain multiple options:

| Option | Description | Behaviour when not present in *param_Options* |
| :--- | :--- | :--- |
| ~~"B"~~ | ~~Block mouse input while it's moving~~ | ~~Doesn't block by default~~ |
| "i<b>0</b>" or "i<b>1</b>" | Clockwise "i<b>0</b>" or counterclockwise ("i<b>1</b>") movement | Random by default |
| "R" | Indicates that the destination coordinates should be relative | Not relative by default |
| "S<b>{number}</b>" | Movement speed (from 0 exclusive to 1 inclusive) | Defaults to 1 |
| "OX<b>{number}</b>" | Origin X coordinate | Defaults to the current mouse X coordinate |
| "OY<b>{number}</b>" | Origin Y coordinate | Defaults to the current mouse Y coordinate |

-----------------------

## Changelog (YYYY-MM-DD)

##### 2017-02-13
* Initial release
