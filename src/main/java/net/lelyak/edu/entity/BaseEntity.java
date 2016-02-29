package net.lelyak.edu.entity;

public abstract class BaseEntity {

    private Long id = null;
    private String name;

    public BaseEntity() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BaseEntity [id=" + id + ", name=" + name + "]";
    }

}