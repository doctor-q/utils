package cc.doctor.utils.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class NestedMapBuilder {
    /**
     * root map node
     */
    private MapNode root;
    /**
     * current map node
     */
    private MapNode current;

    /**
     * nested map level
     */
    private int level = 0;

    public static NestedMapBuilder newMap() {
        NestedMapBuilder nestedMapBuilder = new NestedMapBuilder();
        nestedMapBuilder.root = MapNode.newNode();
        nestedMapBuilder.current = nestedMapBuilder.root;
        return nestedMapBuilder;
    }

    public NestedMapBuilder startSubMap(String key) {
        MapNode mapNode = MapNode.newNode();
        current.getMap().put(key, mapNode.getMap());
        mapNode.prev = current;
        current = mapNode;
        level++;
        return this;
    }

    public NestedMapBuilder endSubMap() {
        current = current.prev;
        level--;
        return this;
    }

    public NestedMapBuilder put(String key, Object value) {
        current.getMap().put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        if (level != 0) {
            throw new RuntimeException("Level is not 0");
        }
        return root.getMap();
    }

    /**
     * a map has a point to parent
     */
    static class MapNode {
        private Map<String, Object> map;
        private MapNode prev;

        static MapNode newNode() {
            MapNode mapNode = new MapNode();
            mapNode.map = new LinkedHashMap<>();
            mapNode.prev = mapNode;
            return mapNode;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }

        public MapNode getPrev() {
            return prev;
        }

        public void setPrev(MapNode prev) {
            this.prev = prev;
        }
    }
}
