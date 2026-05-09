import os
import re

base_path = r"D:\LifeSync\src\com\lifesync"

for root, _, files in os.walk(base_path):
    for file in files:
        if file.endswith(".java"):
            file_path = os.path.join(root, file)
            
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Find and replace com.lifesync.ClassName with ClassName
            # Because we already added wildcard imports.
            new_content = re.sub(r'\bcom\.lifesync\.([A-Z]\w*)', r'\1', content)
            
            if new_content != content:
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(new_content)

print("Removed explicit com.lifesync prefixes.")
